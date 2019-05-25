package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.stream.Collectors;

public class ArticleDaoJPA implements Dao<Article, NewArticle>{

    private EntityManager em;

    public ArticleDaoJPA(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Article> getAll(){
        em.getTransaction().begin();
      Query q = em.createQuery("From ArticleEntity");
      return List.ofAll(q.getResultStream());
    }

    @Override
    public Article get(long id) {
        return null;
    }

    @Override
    public void save(NewArticle obj) {

    }

    @Override
    public void delete(Article obj) {

    }

    @Override
    public void update(Article obj) {

    }
}
