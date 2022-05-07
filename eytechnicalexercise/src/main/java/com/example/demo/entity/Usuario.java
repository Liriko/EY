package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@ToString
@Table(name = "tbl_user", uniqueConstraints=@UniqueConstraint(columnNames = {"email"}))
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "El campo nombre no debe ser vacío")
	private String name;
	@NotEmpty(message = "El campo correo no debe ser vacío") 
	@Email(message = "El campo correo no posee el formato correcto")
	private String email;
	@NotEmpty 
	@Size(min = 8, max = 16, message = "El campo contraseña debe ser superior a 8 caracteres and menor que 16 caracteres")
	private String password;
	@CreationTimestamp
	@Column(name = "created", nullable = false, updatable = false)
	private Date created;
	@UpdateTimestamp
	@Column(name = "modified")
	private Date modified;
	@LastModifiedDate
	@Column(name = "last_login")
	private Date lastLogin;
	private UUID token;
	private boolean isActive;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<Phone> phones;

	@Override
	public int hashCode() {
		return Math.toIntExact(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else {
			Usuario other = (Usuario) obj;
			return email.equals(other.email);
		}
	}
}
