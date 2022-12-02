package hello.springmvc.basic.requestMappping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/usrs")
public class MappingClassCntroller {

    @GetMapping
    public String user(){
        return "get users";
    }

    @PostMapping
    public String addUser(){
        return "get users";
    }

    @GetMapping("/{userID}")
    public String findUser(@PathVariable String userID){
        return "post users"+userID;
    }

    @PatchMapping("/{userID}")
    public String updateUser(@PathVariable String userID){
        return "update users"+userID;
    }

    @DeleteMapping("/mapping/usrs/{userID}")
    public String deleteUser(@PathVariable String userID){
        return "delete users"+userID;
    }

}
