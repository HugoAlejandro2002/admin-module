package com.admin.Admin.services;

import com.admin.Admin.models.Item;
import com.admin.Admin.models.User;

import java.util.List;
public interface AdminService {
    User save(User user);
    User getUserByID(String id);
    List<User> getAllUsers();
    User updateUser(String id, User updatedUser);
    void deleteUser(String id);
    Item saveItem(Item item);
    Item getItemById(String id);
    List<Item> getAllItems();
    Item updateItem(String id, Item updatedItem);
    void deleteItem(String id);
}
