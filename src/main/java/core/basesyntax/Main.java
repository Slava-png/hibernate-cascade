package core.basesyntax;

import core.basesyntax.dao.CommentDao;
import core.basesyntax.dao.MessageDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.dao.impl.CommentDaoImpl;
import core.basesyntax.dao.impl.MessageDaoImpl;
import core.basesyntax.dao.impl.UserDaoImpl;
import core.basesyntax.model.Comment;
import core.basesyntax.model.Message;
import core.basesyntax.model.MessageDetails;
import core.basesyntax.model.Smile;
import core.basesyntax.model.User;
import core.basesyntax.util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        // init classes
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        final CommentDao commentDao = new CommentDaoImpl(sessionFactory);
        final UserDao userDao = new UserDaoImpl(sessionFactory);
        final MessageDao messageDao = new MessageDaoImpl(sessionFactory);

        // test Smile and Comment
        Smile smile = new Smile();
        smile.setValue("happy");

        Comment comment = new Comment();
        comment.setContent("Hello world");
        comment.setSmiles(List.of(smile));

        commentDao.create(comment);

        Comment commentToDelete = commentDao.get(comment.getId());
        commentDao.remove(commentToDelete);

        // test User and Comment
        User user = new User();
        user.setUsername("Escobar");

        Comment comment1 = new Comment();
        comment1.setContent("Hi");
        Comment comment2 = new Comment();
        comment2.setContent("Hello");

        user.setComments(List.of(comment1, comment2));

        userDao.create(user);

        User userForDeletion = userDao.get(user.getId());
        userDao.remove(userForDeletion);

        // test MessageDetails
        MessageDetails messageDetails = new MessageDetails();
        messageDetails.setSender("Harry");
        messageDetails.setSentTime(LocalDateTime.now());

        Message message = new Message();
        message.setContent("Hello");
        message.setMessageDetails(messageDetails);

        messageDao.create(message);

        Message messageToDelete = messageDao.get(message.getId());
        messageDao.remove(messageToDelete);
    }
}
