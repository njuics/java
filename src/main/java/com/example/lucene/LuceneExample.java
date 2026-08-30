package com.example.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.ByteBuffersDirectory;

public class LuceneExample {
    public static void main(String[] args) throws Exception {
        // 1. 创建索引目录（Lucene 9.x 使用 ByteBuffersDirectory 替代 RAMDirectory）
        Directory directory = new ByteBuffersDirectory();
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(directory, config);

        // 2. 添加文档到索引（使用中英文混合，便于演示）
        Document doc1 = new Document();
        doc1.add(new TextField("title", "Java编程指南", org.apache.lucene.document.Field.Store.YES));
        doc1.add(new TextField("content", "这是一本关于Java编程的书籍", org.apache.lucene.document.Field.Store.YES));
        writer.addDocument(doc1);

        Document doc2 = new Document();
        doc2.add(new TextField("title", "Python数据分析", org.apache.lucene.document.Field.Store.YES));
        doc2.add(new TextField("content", "学习Python进行数据分析", org.apache.lucene.document.Field.Store.YES));
        writer.addDocument(doc2);

        Document doc3 = new Document();
        doc3.add(new TextField("title", "Java高级程序设计", org.apache.lucene.document.Field.Store.YES));
        doc3.add(new TextField("content", "深入理解Java虚拟机", org.apache.lucene.document.Field.Store.YES));
        writer.addDocument(doc3);

        writer.close();

        // 3. 搜索（使用 QueryParser 进行更智能的查询）
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        // 使用 QueryParser 解析查询字符串，会自动处理分词
        StandardAnalyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("content", analyzer);
        Query query = parser.parse("Java");  // 搜索包含 "Java" 的文档
        
        TopDocs results = searcher.search(query, 10);

        // 4. 显示结果
        System.out.println("搜索关键词: Java");
        System.out.println("搜索结果数量: " + results.totalHits.value);
        System.out.println("---");
        
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("标题: " + doc.get("title"));
            System.out.println("内容: " + doc.get("content"));
            System.out.println("得分: " + scoreDoc.score);
            System.out.println("---");
        }

        reader.close();
    }
}
