//package com.gasq.bdp.logn;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.gasq.bdp.logn.component.MongoFactory;
//import com.gasq.bdp.logn.mapper.TSysTimerMongoMapper;
//import com.gasq.bdp.logn.model.TSysTimerMongo;
//import com.gasq.bdp.logn.utils.CommonUtils;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MongoWorkFlowApplicationTests {
//	@Autowired MongoFactory mongoFactory;
//	@Autowired TSysTimerMongoMapper mongoMapper;
//	
//	MongoClient mc = null;
//	
//	MongoDatabase mongoDatabase = null;
//	
//	MongoCollection<Document> collection = null;
//	
//	@Before
//	public void initMongoClient() throws Exception {
//		// 获取mongo联系信息
//		TSysTimerMongo bean = mongoMapper.selectByPrimaryKey(1);
//		// 连接到 mongodb 服务
//		mc = mongoFactory.getMongoClient(bean);
//		// 连接到数据库
//		mongoDatabase = mc.getDatabase(bean.getMgDatabase());  
//        System.out.println("Connect to database successfully");
//        // 连接数据库表
//        collection = mongoDatabase.getCollection(bean.getMgCollection());
//        System.out.println("集合 test 选择成功");
//	}
//	//检索所有文档  
//    /** 
//    * 1. 获取迭代器FindIterable<Document> 
//    * 2. 获取游标MongoCursor<Document> 
//    * 3. 通过游标遍历检索出的文档集合 
//    * */  
//	@Test
//	public void findAll() {
//        FindIterable<Document> findIterable = collection.find();  
//        MongoCursor<Document> mongoCursor = findIterable.iterator();  
//        while(mongoCursor.hasNext()){  
//           System.out.println(mongoCursor.next().toJson());  
//        }  
//	}
//	@SuppressWarnings("unchecked")
//	@Test
//	public void find() {
//		FindIterable<Document> findIterable = collection.find(Filters.eq("keyword", "大师傅"));  
//        MongoCursor<Document> mongoCursor = findIterable.iterator();  
//        while(mongoCursor.hasNext()){
//        	Document doc = mongoCursor.next();
//        	String json = CommonUtils.BeanToJSON(doc);
//        	System.out.println(json);
//        	Map<String,Object> map = CommonUtils.json2Bean(json, Map.class);
//        	System.out.println(map.toString());
//        }  
//	}
//	
//	@Test
//	public void update() {
//		//更新文档   将文档中likes=100的文档修改为likes=200   
//        collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
//        //检索查看结果  
//        FindIterable<Document> findIterable = collection.find();  
//        MongoCursor<Document> mongoCursor = findIterable.iterator();  
//        while(mongoCursor.hasNext()){  
//           System.out.println(mongoCursor.next());  
//        }
//	}
//	@Test
//	public void delete() {
//		//删除符合条件的第一个文档  
//        collection.deleteOne(Filters.eq("likes", 200));  
//        //删除所有符合条件的文档  
//        collection.deleteMany (Filters.eq("likes", 200));  
//        //检索查看结果  
//        FindIterable<Document> findIterable = collection.find();  
//        MongoCursor<Document> mongoCursor = findIterable.iterator();  
//        while(mongoCursor.hasNext()){  
//          System.out.println(mongoCursor.next());  
//        } 
//	}
//	//插入文档  
//    /** 
//    * 1. 创建文档 org.bson.Document 参数为key-value的格式 
//    * 2. 创建文档集合List<Document> 
//    * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
//    * */
//	@Test
//	public void add() {
//        Document document = new Document("title", "MongoDB").append("description", "database"). append("likes", 100).append("by", "Fly");
//        List<Document> documents = new ArrayList<Document>();  
//        documents.add(document);  
//        collection.insertMany(documents);  
//        System.out.println("文档插入成功");  
//	}
//	
//	@Test
//	public void clear() {
//		mc.close();
//	}
//	
//	/**
//	   * 模糊查询
//	   * 
//	   * @param db
//	   * @param name
//	   */
//	  public static void query(DB db,String name){
//	    DBCollection dbColl = db.getCollection(name);
//	    //完全匹配
//	    //Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
//	    //右匹配
//	    //Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
//	    //左匹配
//	    //Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
//	    //模糊匹配
//	    Pattern pattern = Pattern.compile("^.*name8.*$", Pattern.CASE_INSENSITIVE);
//	    BasicDBObject query = new BasicDBObject();
//	    query.put("name",pattern);
//	    BasicDBObject sort = new BasicDBObject();
//	    // 1,表示正序； －1,表示倒序
//	    sort.put("name",1);
//	    DBCursor cur = dbColl.find(query).sort(sort);
//	    int count = 0;
//	    while (cur.hasNext()) {
//	      DBObject obj = cur.next();
//	      System.out.print("name=" + obj.get("name"));
//	      System.out.print(",email=" + obj.get("email"));
//	      System.out.println(",passwd=" + obj.get("passwd"));
//	      count ++;
//	    }
//	    System.out.println("共有： " + count + "个");
//	  }
//	    
//	  
//	  /**
//	   * 分页查询
//	   * 
//	   * @param db
//	   * @param name
//	   * @param start
//	   * @param pageSize
//	   */
//	  public static void page(DB db,String name,int start,int pageSize){
//	    DBCollection dbColl = db.getCollection(name);
//	    BasicDBObject sort = new BasicDBObject();
//	    sort.put("name",1);
//	    DBCursor cur = dbColl.find().sort(sort).skip(start).limit(pageSize);;
//	    int count = 0;
//	    while (cur.hasNext()) {
//	      DBObject obj = cur.next();
//	      System.out.print("name=" + obj.get("name"));
//	      System.out.print(",email=" + obj.get("email"));
//	      System.out.println(",passwd=" + obj.get("passwd"));
//	      count ++;
//	    }
//	    System.out.println("共有： " + count + "个");
//	  }
//	  /**
//	   * @param args
//	   * @throws Exception 
//	   */
//	  public static void main(String[] args) throws Exception {
//	    DB db = getDB("demo");
//	    collections(db);
//	    String name = "users";
//	    System.out.println("count()=================================================");
//	    count(db,name);
//	    System.out.println("query()=================================================");
//	    query(db,name);
//	    System.out.println("page()=================================================");
//	    page(db,name,10, 10);
//	  }
//}
