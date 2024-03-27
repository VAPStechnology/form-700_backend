package com.ReviewCollection.review.service;

import com.ReviewCollection.review.dto.UserDto;
import com.ReviewCollection.review.entity.User;
import com.ReviewCollection.review.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public User createUser(UserDto userDto) {
        User u=new User();
        u.setEmail(userDto.getEmail());
        u.setMobileNumber(userDto.getMobileNumber());
        u.setUsername(userDto.getUsername());
        return userRepository.save(u);
    }

    @Override
    public User updateUser(Long userId, User user) {
        // Retrieve the user from the database by ID
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update the user details with the new values
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        // Update other fields as needed

        // Save the updated user entity back to the database
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void approveUser(Long userId) {
        // Retrieve the user from the database by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update the user's approval status
        user.setVerified(true);

        // Save the updated user entity back to the database
        userRepository.save(user);
    }

    @Override
    public void blockUser(Long userId) {
        // Retrieve the user from the database by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update the user's block status
        user.setActive(false);

        // Save the updated user entity back to the database
        userRepository.save(user);
    }

    @Override
    public void sendEmailAfterInput(Long userId) {

    }

    @Override
    public void verifyUser(Long userId) {

    }
    @Override
    public void logLoginTime(Long userId) {
        // Retrieve the user from the database by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update the user's login time
        user.setLoginTime(LocalDateTime.now());

        // Save the updated user entity back to the database
        userRepository.save(user);
    }

    @Override
    public void logLogoutTime(Long userId) {
        // Retrieve the user from the database by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update the user's logout time
        user.setLogoutTime(LocalDateTime.now());

        // Save the updated user entity back to the database
        userRepository.save(user);
    }

    @Override
    public User getUserActivity(Long userId) {
        // Retrieve the user from the database by ID
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    @Override
    public boolean sendOTP(String email, String mobileNumber, String otp) {
        // Send OTP to user's email or mobile number
        //return emailService.sendOTP(email, otp);
        return false;
    }

    @Override
    public boolean verifyOTP(String email, String otp) {
        // Verify OTP
        String storedOTP = emailService.getStoredOTP(email);

        // Verify if the provided OTP matches the stored OTP
        return otp.equals(storedOTP);
    }

    @Override
    public void saveCredentials(String email, String username, String password) {
// Save username and password in the database
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public boolean sendCredentials(String email, String username, String password) {
        // Send username and password to user's email
        return false;
    }
}