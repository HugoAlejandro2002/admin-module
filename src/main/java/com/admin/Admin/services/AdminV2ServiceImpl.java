package com.admin.Admin.services;

import com.admin.Admin.entities.ItemEntity;
import com.admin.Admin.entities.UserEntity;
import com.admin.Admin.errors.ItemNotFoundException;
import com.admin.Admin.errors.UserNotFoundException;
import com.admin.Admin.models.Item;
import com.admin.Admin.models.User;
import com.admin.Admin.repositories.ItemRepository;
import com.admin.Admin.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AdminV2ServiceImpl implements  AdminService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public User save(User user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID().toString());
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user,entity);
        userRepository.save(entity);
        return user;
    }

    @Override
    public User getUserByID(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> {
                    User user = new User();
                    BeanUtils.copyProperties(userEntity, user);
                    return user;
                })
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(String id, User updatedUser) {
        UserEntity existingUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        BeanUtils.copyProperties(updatedUser, existingUserEntity);
        userRepository.save(existingUserEntity);

        return updatedUser;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Item saveItem(Item item) {
        if (item.getItemId() == null) {
            item.setItemId(UUID.randomUUID().toString());
        }
        ItemEntity entity = new ItemEntity();
        BeanUtils.copyProperties(item, entity);
        itemRepository.save(entity);
        return item;
    }

    @Override
    public Item getItemById(String id) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));

        Item item = new Item();
        BeanUtils.copyProperties(itemEntity, item);
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<ItemEntity> itemEntities = itemRepository.findAll();
        return itemEntities.stream()
                .map(itemEntity -> {
                    Item item = new Item();
                    BeanUtils.copyProperties(itemEntity, item);
                    return item;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Item updateItem(String id, Item updatedItem) {
        ItemEntity existingItemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));

        BeanUtils.copyProperties(updatedItem, existingItemEntity);
        itemRepository.save(existingItemEntity);

        return updatedItem;
    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
