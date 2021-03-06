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

import com.commerce.cdbookstore.model.Category;

/**
 * Backing bean for Category entities.
 * <p/>
 * This class provides CRUD functionality for all Category entities. It focuses purely on Java EE 6 standards (e.g.
 * <tt>&#64;ConversationScoped</tt> for state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving Category entities
    */

    private Long id;
    private Category category;
    @Inject
    private Conversation conversation;
    @PersistenceContext(unitName = "applicationCDBookStorePU", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    private int page;
    private long count;
    private List<Category> pageItems;
    private Category example = new Category();
    @Resource
    private SessionContext sessionContext;
    private Category add = new Category();

    public Long getId() {
        return this.id;
    }

   /*
    * Support updating and deleting Category entities
    */

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

   /*
    * Support searching Category entities with pagination
    */

    public void setCategory(Category category) {
        this.category = category;
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
            this.category = this.example;
        } else {
            this.category = findById(getId());
        }
    }

    public Category findById(Long id) {

        return this.entityManager.find(Category.class, id);
    }

    public String update() {
        this.conversation.end();

        try {
            if (this.id == null) {
                this.entityManager.persist(this.category);
                return "search?faces-redirect=true";
            } else {
                this.entityManager.merge(this.category);
                return "view?faces-redirect=true&id=" + this.category.getId();
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
            Category deletableEntity = findById(getId());

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

    public Category getExample() {
        return this.example;
    }

    public void setExample(Category example) {
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
        Root<Category> root = countCriteria.from(Category.class);
        countCriteria = countCriteria.select(builder.count(root)).where(
                getSearchPredicates(root));
        this.count = this.entityManager.createQuery(countCriteria)
                .getSingleResult();

        // Populate this.pageItems

        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        root = criteria.from(Category.class);
        TypedQuery<Category> query = this.entityManager.createQuery(criteria
                .select(root).where(getSearchPredicates(root)));
        query.setFirstResult(this.page * getPageSize()).setMaxResults(
                getPageSize());
        this.pageItems = query.getResultList();
    }

    private Predicate[] getSearchPredicates(Root<Category> root) {

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
    * Support listing and POSTing back Category entities (e.g. from inside an HtmlSelectOneMenu)
    */

    public List<Category> getPageItems() {
        return this.pageItems;
    }

    public long getCount() {
        return this.count;
    }

    public List<Category> getAll() {

        CriteriaQuery<Category> criteria = this.entityManager
                .getCriteriaBuilder().createQuery(Category.class);
        return this.entityManager.createQuery(
                criteria.select(criteria.from(Category.class))).getResultList();
    }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

    @SuppressWarnings("rawtypes")
	public Converter getConverter() {

        final CategoryBean ejbProxy = this.sessionContext
                .getBusinessObject(CategoryBean.class);

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

                return String.valueOf(((Category) value).getId());
            }
        };
    }

    public Category getAdd() {
        return this.add;
    }

    public Category getAdded() {
        Category added = this.add;
        this.add = new Category();
        return added;
    }
}
