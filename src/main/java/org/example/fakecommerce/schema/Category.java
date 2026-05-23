package org.example.fakecommerce.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql= "UPDATE category SET deleted_at= CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@Table(name= "category")
public class Category extends BaseClass{

    @Column(nullable = false)
    public String name;

}
