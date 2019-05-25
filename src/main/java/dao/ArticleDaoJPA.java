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
      List<Article> result = List.ofAll(q.getResultStream()
      .map(e -> new Article((ArticleEntity) e)));
      em.getTransaction().commit();
      return result;
    }

    @Override
    public Article get(long id) {

        em.getTransaction().begin();

       ArticleEntity a = (ArticleEntity) em.createQuery("select a from ArticleEntity a where id= " +id).getSingleResult();
        em.getTransaction().commit();
        return new Article(a);
    }

    @Override
    public void save(NewArticle obj) {
        ArticleEntity ae = new ArticleEntity(obj);
        em.getTransaction().begin();
        em.persist(ae);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Article obj) {
        em.getTransaction().begin();
        em.remove(new ArticleEntity(obj));
        em.getTransaction().commit();
    }

    @Override
    public void update(Article obj) {
        throw new UnsupportedOperationException();
    }
}
