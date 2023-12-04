package com.admin.Admin.services;

import com.admin.Admin.errors.UserNotFoundException;
import com.admin.Admin.models.Item;
import com.admin.Admin.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements  AdminService{
    private final List<User> userList = new ArrayList<>();

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID().toString());
        }
        userList.add(user);
        return user;
    }

    @Override
    public User getUserByID(String id) {
        Optional<User> optionalUser = userList.stream()
                .filter(u -> u.getUserId().equalsIgnoreCase(id))
                .findFirst();
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User updateUser(String id, User updatedUser) {
        Optional<User> optionalUser = userList.stream()
                .filter(u -> u.getUserId().equalsIgnoreCase(id))
                .findFirst();

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            return existingUser;
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public void deleteUser(String id) {
        userList.removeIf(u -> u.getUserId().equalsIgnoreCase(id));
    }

    @Override
    public Item saveItem(Item item) {
        return null;
    }

    @Override
    public Item getItemById(String id) {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public Item updateItem(String id, Item updatedItem) {
        return null;
    }

    @Override
    public void deleteItem(String id) {

    }
}
