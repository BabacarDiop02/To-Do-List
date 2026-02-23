package sn.diop.babacar.minitodolist.entity;

import sn.diop.babacar.minitodolist.entity.enums.TacheStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tache")
@Entity
public class TacheEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tache_titre")
    private String titre;
    @Column(name = "tache_description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "tache_statut")
    private TacheStatusType statut;
}
