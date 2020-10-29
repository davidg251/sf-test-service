package com.acalderon.demoBack;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(value = SpringRunner.class)
@DataJpaTest
public class UserRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;


    @Test
    public void deleteWorks() {
        User alex = new User("alex");
        userRepo.delete(alex);

        User found = userRepo.findByName(alex.getName());
        Assert.assertNull(found);

    }

    @Test
    public void whenFindByNameReturnEmployee() {
        // given
        User alex = new User("alex");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        User found = userRepo.findByName(alex.getName());

        // then
        assertEquals(found.getName(),alex.getName());
    }


    @Test
    public void findUserExists() {
        String userName = "Julie";

        User found = userRepo.findByName(userName);
        assertEquals(found.getName(), userName);
    }

    @Test
    public void userDontExists() {
        User found = userRepo.findByName("Pepe");
        Assert.assertNull(found);
    }
}