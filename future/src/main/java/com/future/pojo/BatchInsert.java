package com.future.pojo;

import com.future.repository.BatchOperateMysqlDao;
import com.future.utils.BathUtil;

import java.util.List;
import java.util.concurrent.*;

public class BatchInsert implements Callable<Boolean> {

    /**
     * 100条为分界批量导入
     */
    private int batch100 = 100;
    /**mysql数据*/

    private List<GeneralTable> list;
    private BatchOperateMysqlDao batchOperateMysqlDao;

    /**线程池*/
    private ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(2,
                    Runtime.getRuntime().availableProcessors(),
                    2L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(100),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );
    /**
     *这里的对象batchOperateMysqlDao是从实现类传过来的，因为这个类本身没有纳入容器管理
     *所以不能直接用Autowire引入dao层对象
     **/
    public BatchInsert(List<GeneralTable> list, BatchOperateMysqlDao batchOperateMysqlDao) {
        this.list = list;
        this.batchOperateMysqlDao = batchOperateMysqlDao;
    }
    public BatchInsert(List<GeneralTable> list) {
        this.list = list;
    }
    @Override
    public Boolean call(){
        try {
            batchOp(list);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private void batchOp(List<GeneralTable> list) {
        System.out.println("我是线程："+Thread.currentThread().getName());
        if(!list.isEmpty()){
            Integer size = list.size();
            if(size<=batch100){
                batchOperateMysqlDao.saveAll(list);
            }else if(size>batch100){
                batchOpSpilit(list,batch100);
            }
        }
    }
    //切割
    private void batchOpSpilit(List<GeneralTable> list, int batch100) {
        System.out.println("开始切割………………");
        Long t1 = System.currentTimeMillis();
        List<List<GeneralTable>> list1 = BathUtil.pagingList(list,batch100);
        try {
            for(List<GeneralTable> list2:list1){
                //再调batchOp方法，这里的多线程是多个小集合往数据库插
                threadPoolExecutor.execute(()->{
                    System.out.println("我是线程："+Thread.currentThread().getName() );
                    batchOp(list2);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
            Long t2 = System.currentTimeMillis();
            System.out.println("执行完成,用时…………"+(t2-t1));
        }
    }

}
