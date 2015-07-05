package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public String id;

    public String name;
}
