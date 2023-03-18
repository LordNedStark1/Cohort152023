package model.users;

import repositories.RepositoryInterface;

public class Admin extends User{
    public Admin(RepositoryInterface repo) {
        super(repo);
    }
}
