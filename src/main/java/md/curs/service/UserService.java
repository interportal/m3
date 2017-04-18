package md.curs.service;

import md.curs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Simulate an user service before the DB was known to us ;)
 *
 * Created by MG
 */
@Service
public class UserService {

    private List<User> mockUsers = new ArrayList<>();

    private long maxId = 0;

    @Autowired
    public UserService() {
        Random r = new Random();

        // Generate 10 users with random ages
        for (int i = 0; i < 10; i++) {
            User u = new User((long) i, "Name" + i, "Surname" + i,
                    null, r.nextInt(50));
            mockUsers.add(u);
            maxId = i;
        }
    }

    /**
     * Get an User by it's ID
     *
     * @param id the id of the user
     * @return an {@link Optional} User (present only if the user with specified id exists)
     */
    public Optional<User> getUser(long id) {
        return mockUsers.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    /**
     * Find all users which contain in their name or surname the specified query
     *
     * @param query query to find by
     * @return the filtered user list
     */
    public List<User> findUsers(String query) {
        if (query == null) query = "";
        String finalQuery = query;

        // find users which contain in their name or surname the specified query
        return mockUsers.stream()
                .filter(u -> u.getName().contains(finalQuery) || u.getSurname().contains(finalQuery))
                .collect(Collectors.toList());
    }

    /**
     * Get the count of users which have the age below 18
     *
     * @return minors count
     */
    public long getMinorsCount() {
        return mockUsers.stream().filter(u -> u.getAge() < 18).count();
    }

    /**
     * Add a new user or update an existing one depending on the provided user.id
     *
     * @param user The user to save
     */
    public void saveUser(User user) {
        if (user.getId() == null) {
            // if user has no ID just set one and add to list
            user.setId(++maxId);
            mockUsers.add(user);
        } else {
            // get the user from list which has the same id
            Optional<User> replaceUser = mockUsers.stream()
                    .filter(u -> u.getId().equals(user.getId()))
                    .findFirst();

            if (replaceUser.isPresent()) {
                // update the user if it is found
                int replaceUserIndex = mockUsers.indexOf(replaceUser.get());
                mockUsers.set(replaceUserIndex, user);
            } else {
                // no user found with the specified id => add it to the list then
                mockUsers.add(user);
            }
        }
    }

    /**
     * Remove an User
     *
     * @param u user to remove
     */
    public void removeUser(User u) {
        mockUsers.remove(u);
    }
}
