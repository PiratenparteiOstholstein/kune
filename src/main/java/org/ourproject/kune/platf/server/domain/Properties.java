package org.ourproject.kune.platf.server.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

/**
 * Properties defined for some elements (identified by the group)
 * 
 */
@Entity
@Table(name = "properties")
public class Properties implements HasId {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Every list of properties are from a unique PropertyGroup (like User
     * properties, or Group properties)
     * 
     */
    @NotNull
    @ManyToOne
    private final PropertyGroup pgroup;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "property")
    // @Cascade( { org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    // @OnDelete(action = OnDeleteAction.CASCADE)
    private Map<Property, PropertySetted> list;

    public Properties() {
        this(null);
    }

    public Properties(final PropertyGroup group) {
        pgroup = group;
        list = new HashMap<Property, PropertySetted>();
    }

    // @Finder(query =
    // "SELECT p FROM Properties ps JOIN ps.list p WHERE p.property.name = :pname")
    // public PropertySetted find(@Named("pname") final String key) {
    // return null;
    // }

    public Long getId() {
        return id;
    }

    public Map<Property, PropertySetted> getList() {
        return list;
    }

    public PropertyGroup getPgroup() {
        return pgroup;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setList(final Map<Property, PropertySetted> list) {
        this.list = list;
    }

}
