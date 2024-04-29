package project.keyappsk.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import project.keyappsk.domain.alarm.entity.AlarmMessage;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.entity.enumerate.SignType;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.store.entity.Store;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SignType signType;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Store> stores = new ArrayList<>();;

    @OneToMany(mappedBy = "memberBuyer")
    @ToString.Exclude
    private List<Order> orders;

    @OneToMany(mappedBy = "receiver")
    @ToString.Exclude
    private List<AlarmMessage> alarmMessages;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Cart> carts;



    public Member update(String name){
        this.name = name;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }


}
