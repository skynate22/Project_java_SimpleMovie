package persistence;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import domain.ReplyDTO;

public class ReplyDAO {
	MongoClient client = MongoClients.create();
	MongoDatabase db = client.getDatabase("local");
	MongoCollection<Document> collection = db.getCollection("movie");
	
	public void addReply(ReplyDTO rDto) {
		Document doc = new Document("movieNm",rDto.getMovieNm())
				.append("content", rDto.getContent())
				.append("writer", rDto.getWriter())
				.append("score", rDto.getScore())
				.append("regdata", rDto.getRegdata());
		// 댓글 1건등록
		collection.insertOne(doc); //  One 1건    Many 다수의건
	}
	//댓글 삭제(등록하려는 영화의 댓글이 존재할 때  해당 영화 댓글만 삭제)
	public void deleteReply(String movieNm) {
		collection.deleteMany(new Document("movieNm", movieNm)); // movieNm getMovieNm 이랑 같은걸 전부 삭제하세요 라는뜻
	}
}
