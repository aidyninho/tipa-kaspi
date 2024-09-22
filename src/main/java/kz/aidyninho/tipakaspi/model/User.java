package kz.aidyninho.tipakaspi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String phone;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Account> accounts = new HashSet<>();
}
