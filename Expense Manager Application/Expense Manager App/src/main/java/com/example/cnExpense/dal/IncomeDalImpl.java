package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import com.example.cnExpense.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class IncomeDalImpl implements IncomeDal {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserService userService;

    @Override
    public Income getIncomeById(Long incomeId) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Income.class, incomeId);
    }

    @Override
    public Income saveIncome(User user, Income newIncome) {
        Session session = entityManager.unwrap(Session.class);

        // Save new income
        session.save(newIncome);

        // Retrieve and associate the user with the income
        User existingUser = userService.getUserById(user.getId());
        existingUser.getIncomes().add(newIncome);
        newIncome.setUser(existingUser);

        session.saveOrUpdate(newIncome);
        session.saveOrUpdate(existingUser);

        return newIncome;
    }

    @Override
    public Income updateIncome(Long incomeId, Income income) {
        Session session = entityManager.unwrap(Session.class);

        // Retrieve the existing income
        Income existingIncome = session.get(Income.class, incomeId);
        if (existingIncome == null) {
            throw new RuntimeException("Income with ID " + incomeId + " not found");
        }

        // Update fields only if they are not null or empty in the incoming income object

        if (income.getAmount() != 0) { // Assuming 0 is not a valid value for amount if unchanged
            existingIncome.setAmount(income.getAmount());
        }
        if (income.getDate() != null) {
            existingIncome.setDate(income.getDate());
        }
        if (income.getDescription() != null && !income.getDescription().isEmpty()) {
            existingIncome.setDescription(income.getDescription());
        }
        if (income.getIncomeType() != null && !income.getIncomeType().isEmpty()) {
            existingIncome.setIncomeType(income.getIncomeType());
        }

        // Save the updated income
        session.update(existingIncome);

        return existingIncome;
    }

}


