package com.fishmon.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fishmon.Model.Dao.User;
import com.fishmon.Model.dto.Response;
import com.fishmon.Repository.UserRepository;
import com.fishmon.Services.UserService;
import com.fishmon.Utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public Response<User> register(@RequestBody User user) {
        Response<User> res = new Response<>();

        try {
            User newUser = userService.registerUser(user);
            res.setStatus(HttpStatus.CREATED.toString());
            res.setMessage("Registrasi berhasil");
            res.setPayload(newUser);
        } catch (Exception e) {
            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            res.setMessage("Registrasi gagal: " + e.getMessage());
            res.setPayload(null);
        }

        return res;
    }

   @PostMapping("/login")
public Response<Map<String, String>> login(@RequestBody User loginUser) {
    Response<Map<String, String>> res = new Response<>();
    Map<String, String> data = new HashMap<>();

    Optional<User> user = userService.findByUsername(loginUser.getUsername());

    if (user.isPresent() && passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword())) {
        String token = jwtUtil.generateToken(user.get().getUsername());
        data.put("token", token);

        res.setStatus(HttpStatus.OK.toString());
        res.setMessage("Login berhasil");
        res.setPayload(data);
    } else {
        res.setStatus(HttpStatus.UNAUTHORIZED.toString());
        res.setMessage("Username atau password salah");
        res.setPayload(null);
    }

    return res;
}

}
