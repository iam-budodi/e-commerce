package com.commerce.cdbookstore.view.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.commerce.cdbookstore.model.Publisher;

/**
 * Backing bean for Publisher entities.
 * <p/>
 * This class provides CRUD functionality for all Publisher entities. It focuses purely on Java EE 6 standards (e.g.
 * <tt>&#64;ConversationScoped</tt> for state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class PublisherBean implements Serializable {

    private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Publisher entities
    */

    private Long id;
    private Publisher publisher;
    @Inject
    private Conversation conversation;
    @PersistenceContext(unitName = "applicationCDBookStorePU", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    private int page;
    private long count;
    private List<Publisher> pageItems;
    private Publisher example = new Publisher();
    @Resource
    private SessionContext sessionContext;
    private Publisher add = new Publisher();

    public Long getId() {
        return this.id;
    }

   /*
    * Support updating and deleting Publisher entities
    */

    public void setId(Long id) {
        this.id = id;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

   /*
    * Support searching Publisher entities with pagination
    */

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String create() {

        this.conversation.begin();
        this.conversation.setTimeout(1800000L);
        return "create?faces-redirect=true";
    }

    public void retrieve() {

        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }

        if (this.conversation.isTransient()) {
            this.conversation.begin();
            this.conversation.setTimeout(1800000L);
        }

        if (this.id == null) {
            this.publisher = this.example;
        } else {
            this.publisher = findById(getId());
        }
    }

    public Publisher findById(Long id) {

        return this.entityManager.find(Publisher.class, id);
    }

    public String update() {
        this.conversation.end();

        try {
            if (this.id == null) {
                this.entityManager.persist(this.publisher);
                return "search?faces-redirect=true";
            } else {
                this.entityManager.merge(this.publisher);
                return "view?faces-redirect=true&id=" + this.publisher.getId();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public String delete() {
        this.conversation.end();

        try {
            Publisher deletableEntity = findById(getId());

            this.entityManager.remove(deletableEntity);
            this.entityManager.flush();
            return "search?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return 10;
    }

    public Publisher getExample() {
        return this.example;
    }

    public void setExample(Publisher example) {
        this.example = example;
    }

    public String search() {
        this.page = 0;
        return null;
    }

    public void paginate() {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        // Populate this.count

        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        Root<Publisher> root = countCriteria.from(Publisher.class);
        countCriteria = countCriteria.select(builder.count(root)).where(
                getSearchPredicates(root));
        this.count = this.entityManager.createQuery(countCriteria)
                .getSingleResult();

        // Populate this.pageItems

        CriteriaQuery<Publisher> criteria = builder
                .createQuery(Publisher.class);
        root = criteria.from(Publisher.class);
        TypedQuery<Publisher> query = this.entityManager.createQuery(criteria
                .select(root).where(getSearchPredicates(root)));
        query.setFirstResult(this.page * getPageSize()).setMaxResults(
                getPageSize());
        this.pageItems = query.getResultList();
    }

    private Predicate[] getSearchPredicates(Root<Publisher> root) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        List<Predicate> predicatesList = new ArrayList<>();

        String name = this.example.getName();
        if (name != null && !"".equals(name)) {
            predicatesList.add(builder.like(
                    builder.lower(root.<String>get("name")),
                    '%' + name.toLowerCase() + '%'));
        }

        return predicatesList.toArray(new Predicate[predicatesList.size()]);
    }

   /*
    * Support listing and POSTing back Publisher entities (e.g. from inside an HtmlSelectOneMenu)
    */

    public List<Publisher> getPageItems() {
        return this.pageItems;
    }

    public long getCount() {
        return this.count;
    }

    public List<Publisher> getAll() {

        CriteriaQuery<Publisher> criteria = this.entityManager
                .getCriteriaBuilder().createQuery(Publisher.class);
        return this.entityManager.createQuery(
                criteria.select(criteria.from(Publisher.class)))
                .getResultList();
    }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

    @SuppressWarnings("rawtypes")
	public Converter getConverter() {

        final PublisherBean ejbProxy = this.sessionContext
                .getBusinessObject(PublisherBean.class);

        return new Converter() {

            @Override
            public Object getAsObject(FacesContext context,
                                      UIComponent component, String value) {

                return ejbProxy.findById(Long.valueOf(value));
            }

            @Override
            public String getAsString(FacesContext context,
                                      UIComponent component, Object value) {

                if (value == null) {
                    return "";
                }

                return String.valueOf(((Publisher) value).getId());
            }
        };
    }

    public Publisher getAdd() {
        return this.add;
    }

    public Publisher getAdded() {
        Publisher added = this.add;
        this.add = new Publisher();
        return added;
    }
}
