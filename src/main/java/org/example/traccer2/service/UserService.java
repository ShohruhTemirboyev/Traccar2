package org.example.traccer2.service;

import org.example.traccer2.entity.RoleName;
import org.example.traccer2.entity.Users;
import org.example.traccer2.payloat.*;
import org.example.traccer2.repository.MessageRepository;
import org.example.traccer2.repository.RoleRepository;
import org.example.traccer2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    RoleRepository roleRepository;


    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Respons save(ReqUser reqUser){
        Respons respons = new Respons();
        try {
            if (userRepository.existsByUserName(reqUser.getUserName())){
             respons.setStatus(messageRepository.findByCode(1002));
            }else {
                Users users=new Users();
                users.setUserName(reqUser.getUserName());
                users.setPassword(reqUser.getPassword());
                users.setEmail(reqUser.getEmail());
                users.setName(reqUser.getName());
                users.setSurname(reqUser.getSurname());
                users.setRole(Collections.singletonList(roleRepository.findByRoleName(RoleName.ROLE_USER)));
                userRepository.save(users);
                respons.setStatus(messageRepository.findByCode(0));
            }
        }catch (Exception e){
            respons.setStatus(messageRepository.findByCode(1));
        }
return respons;
    }


public Respons delete(Integer id){
        Respons respons = new Respons();
        try {
            if (userRepository.existsById(id)){
              userRepository.deleteById(id);
              respons.setStatus(messageRepository.findByCode(0));
            }else {
                respons.setStatus(messageRepository.findByCode(1003));
            }
        }catch (Exception e){
            respons.setStatus(messageRepository.findByCode(1));
        }
        return respons;
}

public Respons getUser(Integer id){
        Respons respons = new Respons();
        try {
            Optional<Users> user = userRepository.findById(id);
            if (user.isPresent()){
                ResUser resUser=new ResUser();
                resUser.setUserName(user.get().getUserName());
                resUser.setEmail(user.get().getEmail());
                resUser.setName(user.get().getName());
                resUser.setSurname(user.get().getSurname());
                resUser.setRole(user.get().getRole());
                respons.setStatus(messageRepository.findByCode(0));
            }else {
             respons.setStatus(messageRepository.findByCode(1003));
            }
        }catch (Exception e){
            respons.setStatus(messageRepository.findByCode(1));
        }
        return respons;
}

public Respons editUser(ReqEditUser reqEdirUser){
        Respons respons=new Respons();
        try {
            Optional<Users> user = userRepository.findById(reqEdirUser.getId());
            if (user.isPresent()){
                user.get().setName(reqEdirUser.getName());
                user.get().setSurname(reqEdirUser.getSurname());
                user.get().setEmail(reqEdirUser.getEmail());
                userRepository.save(user.get());
                respons.setStatus(messageRepository.findByCode(0));
            }else {
                respons.setStatus(messageRepository.findByCode(1003));
            }
        }catch (Exception e){
            respons.setStatus(messageRepository.findByCode(1));
        }
        return respons;
}

      public Respons getAllUser(){
        Respons respons=new Respons();
        try {
            List<ResUser> users=  userRepository.findAll().stream()
                    .map(users1 -> getResUser(users1)).collect(Collectors.toList());
            users.removeIf(resUser -> resUser==null);

              respons.setStatus(messageRepository.findByCode(0));
              respons.setData(users);
        }catch (Exception e){
            respons.setStatus(messageRepository.findByCode(1));
        }
        return respons;
      }

      public ResUser getResUser(Users users){
        ResUser resUser=new ResUser();
        if (users.getActive()) {
            resUser.setUserName(users.getUserName());
            resUser.setEmail(users.getEmail());
            resUser.setName(users.getName());
            resUser.setSurname(users.getSurname());
            resUser.setRole(users.getRole());
            return resUser;
        }else {
           return null;
        }

      }

      public ResUser getResUserRole(Users users) {
          ResUser resUser = new ResUser();
          for (int i = 0; i < users.getRole().size(); i++) {
              if (users.getRole().get(i).equals(RoleName.ROLE_USER)) {
                  resUser.setUserName(users.getUserName());
                  resUser.setEmail(users.getEmail());
                  resUser.setName(users.getName());
                  resUser.setSurname(users.getSurname());
                  resUser.setRole(users.getRole());
              }
          }
          if (resUser.getUserName()!=null){
              return resUser;
          }else {
              return null;
          }

      }

      public Respons getFilter(ReqFilterUser reqFilterUser){
        Respons respons=new Respons();
        try {
            String s="";
            if (reqFilterUser.getUserName()!=null){
                s+="and upper(users.user_name) like upper('%"+reqFilterUser.getUserName()+"%')";
            }
            if (reqFilterUser.getEmail()!=null){
                s+="and upper(users.emil) like upper('%"+reqFilterUser.getEmail()+"%')";
            }
            if (reqFilterUser.getName()!=null){
                s+="and upper(users.name) like upper('%"+reqFilterUser.getName()+"%')";
            }
            if (reqFilterUser.getSurname()!=null){
                s+="and upper(users.surname) like upper('%"+reqFilterUser.getSurname()+"%')";
            }
            List<Map<String,Object>> maps=new ArrayList<>();
            if (s.length()!=0){
                maps=jdbcTemplate.queryForList("select id,name,user_name,surname,email from users where true "+s);
            }else {
                maps=jdbcTemplate.queryForList("select id,name,user_name,surname,email from users");
            }
            respons.setData(maps);
            respons.setStatus(messageRepository.findByCode(0));
        }catch (Exception e){
            respons.setStatus(messageRepository.findByCode(1));
            respons.setData("Error");
        }
        return respons;
      }







}
