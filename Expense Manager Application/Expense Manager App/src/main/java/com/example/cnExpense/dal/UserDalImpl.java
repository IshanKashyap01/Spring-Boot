package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDalImpl implements UserDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
        return user;
    }

    @Override
    public User updateUser(Long userId, User user) {
        Session session = entityManager.unwrap(Session.class);

        // Retrieve the existing user
        User existingUser = session.get(User.class, userId);
        if (existingUser == null) {
            throw new RuntimeException("User with ID " + userId + " not found");
        }

        // Update fields only if they are not null or empty in the incoming user object
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            existingUser.setNickname(user.getNickname());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            existingUser.setAddress(user.getAddress());
        }
        if (user.getExpenses() != null && !user.getExpenses().isEmpty()) {
            existingUser.setExpenses(user.getExpenses());
        }
        if (user.getIncomes() != null && !user.getIncomes().isEmpty()) {
            existingUser.setIncomes(user.getIncomes());
        }

        // Save the updated user
        session.update(existingUser);

        return existingUser;
    }

    @Override
    public User setBudget(Long userId, double budget) {
        Session session = entityManager.unwrap(Session.class);

        // Retrieve the existing user
        User user = session.get(User.class, userId);
        if (user == null) {
            throw new RuntimeException("User with ID " + userId + " not found");
        }

        // Set the new budget
        user.setBudget(budget);

        // Update the user with the new budget value
        session.update(user);

        return user;
    }

    @Override
    public double getTotalExpense(Long userId) {
        User user = getUserById(userId);
        double totalExpense = 0.0;
        for (Expense expense : user.getExpenses()) {
            totalExpense += expense.getAmount();
        }
        return totalExpense;
    }

    @Override
    public double getQuotation(Long userId) {
        User user = getUserById(userId);
        double totalIncome = 0.0;
        for (Income income : user.getIncomes()) {
            totalIncome += income.getAmount();
        }
        double totalExpense = getTotalExpense(userId);
        return totalIncome - totalExpense;
    }

    @Override
    public double getAvgExpenseData(Long userId) {
        User user = getUserById(userId);
        List<Expense> expenses = user.getExpenses();
        if (expenses.isEmpty()) {
            return 0.0;
        }
        double totalExpense = 0.0;
        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }
        return totalExpense / expenses.size();
    }
}


