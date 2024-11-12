package com.atguigu.cloud.service.impl;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.atguigu.cloud.utils.RedisUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import redis.clients.jedis.Jedis;


public class CanalServiceImpl {

    public static final Integer _60SECONDS = 60;
    public static final String REDIS_IP_ADDR = "211.159.166.210";

    private static void redisInsert(List<Column> columns){
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns){
            System.out.println(column.getName()+":"+column.getValue());
            jsonObject.put(column.getName(),column.getValue());
        }
        if (columns.size()>0){
            try(Jedis jedis = RedisUtils.getJedis()) {
                jedis.set(columns.get(0).getValue(),jsonObject.toJSONString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void redisDelete(List<Column> columns){
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns){
            System.out.println(column.getName()+":"+column.getValue());
            jsonObject.put(column.getName(),column.getValue());
        }
        if (columns.size()>0){
            try(Jedis jedis = RedisUtils.getJedis()) {
                jedis.del(columns.get(0).getValue());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void redisUpdate(List<Column> columns){
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns){
            System.out.println(column.getName()+":"+column.getValue());
            jsonObject.put(column.getName(),column.getValue());
        }
        if (columns.size()>0){
            try(Jedis jedis = RedisUtils.getJedis()) {
                jedis.set(columns.get(0).getValue(),jsonObject.toJSONString());
                System.out.println("--update after:"+jedis.get(columns.get(0).getValue()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


//    private static void printEntry(List<Entry> entrys){
//
//    }

    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(REDIS_IP_ADDR,11111),
                "example","","");


        int batchSize = 1000;
        int emptyCount = 0;
        System.out.println("------canal初始化完成，开始监听-----------");
        try {
            connector.connect();
            //connector.subscribe(".*\\..*");
            connector.subscribe("db2024.t_user");
            connector.rollback();
            int totalEmptyCount = 10*_60SECONDS;
            while (emptyCount < totalEmptyCount) {
                System.out.println("canal正在每秒一次监听-------");
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count : " + emptyCount);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                } else {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("已经监听了"+totalEmptyCount+"秒，无任何消息，请重启重试88888888");

        }finally {
            connector.disconnect();
        }

    }

//    public static void main(String args[]) {
//        // 创建链接
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
//                11111), "example", "", "");
//        int batchSize = 1000;
//        int emptyCount = 0;
//        try {
//            connector.connect();
//            connector.subscribe(".*\\..*");
//            connector.rollback();
//            int totalEmptyCount = 120;
//            while (emptyCount < totalEmptyCount) {
//                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId == -1 || size == 0) {
//                    emptyCount++;
//                    System.out.println("empty count : " + emptyCount);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                    }
//                } else {
//                    emptyCount = 0;
//                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
//                    printEntry(message.getEntries());
//                }
//
//                connector.ack(batchId); // 提交确认
//                // connector.rollback(batchId); // 处理失败, 回滚数据
//            }
//
//            System.out.println("empty too many times, exit");
//        } finally {
//            connector.disconnect();
//        }
//    }
//
    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            //获取表名
            String tableName = entry.getHeader().getTableName();
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    redisDelete(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    redisInsert(rowData.getAfterColumnsList());
                } else {
//                    System.out.println("-------&gt; before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------&gt; after");
//                    printColumn(rowData.getAfterColumnsList());
                    redisUpdate(rowData.getAfterColumnsList());
                }
            }
        }
    }
//
    private static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }




}
