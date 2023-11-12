package com.sda.app.controller;

import com.sda.app.dto.CartDto;
import com.sda.app.entity.Cart;
import com.sda.app.entity.Item;
import com.sda.app.entity.User;
import com.sda.app.service.CartService;
import com.sda.app.service.UserService;
import com.sda.app.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCarts(){
      List<Cart> cartList= this.cartService.findAll();
        ApiResponse response=new ApiResponse.Builder()
                .status(200)
                .message("Lista cart generata")
                .data(cartList)
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCart(@RequestBody Cart cart){

        ApiResponse response=new ApiResponse.Builder()
                .status(200)
                .message("Cart creat cu succes")
                .data(cartService.updateCart(cart))
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create-dto")
    public ResponseEntity<ApiResponse> createCartWithDto(@RequestBody CartDto cartDto){
       Optional<User> user = userService.findById(cartDto.getUserId());
       Cart cart = new Cart();
       cart.setItems(cartDto.getItems());
       cart.setUser(user.get());
        ApiResponse response=new ApiResponse.Builder()
                .status(200)
                .message("Cart creat cu succes")
                .data(cartService.updateCart(cart))
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCart(@RequestBody Cart cart, @PathVariable("id")Integer id){

        ApiResponse response=new ApiResponse.Builder()
                .status(200)
                .message("Cart updata cu succes")
                .data(cartService.updateCart(cart))
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("id") Integer id){
        cartService.deleteCart(id);
        ApiResponse response=new ApiResponse.Builder()
                .status(200)
                .message("Cart sters cu succes")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
