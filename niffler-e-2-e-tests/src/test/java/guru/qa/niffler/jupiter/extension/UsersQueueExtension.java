package guru.qa.niffler.jupiter.extension;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class UsersQueueExtension implements
        BeforeEachCallback,
        AfterEachCallback,
        ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(UsersQueueExtension.class);

    public record StaticUser(
            String userName,
            String password,
            String friend,
            String income,
            String outcome) {
    }

    private static final Queue<StaticUser> EMPTY_USERS = new ConcurrentLinkedQueue<>();
    private static final Queue<StaticUser> WITH_FRIEND_USERS = new ConcurrentLinkedQueue<>();
    private static final Queue<StaticUser> WITH_INCOME_REQUEST_USERS = new ConcurrentLinkedQueue<>();
    private static final Queue<StaticUser> WITH_OUTCOME_REQUEST_USERS = new ConcurrentLinkedQueue<>();

    static {
        EMPTY_USERS.add(new StaticUser("empty_user", "qqww11", null, null, null));
        WITH_FRIEND_USERS.add(new StaticUser("svuser", "qqww11", "duck", null, null));
        WITH_INCOME_REQUEST_USERS.add(new StaticUser("test2", "qqww11", null, "test1", null));
        WITH_OUTCOME_REQUEST_USERS.add(new StaticUser("test1", "qqww11", null, null, "test2"));
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserType {
        Type value() default Type.EMPTY;

        enum Type {
            EMPTY,
            WITH_FRIEND,
            WITH_INCOME_REQUEST,
            WITH_OUTCOME_REQUEST
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        List<Parameter> paramsList = Arrays.stream(context.getRequiredTestMethod().getParameters())
                .filter(parameter -> AnnotationSupport.isAnnotated(parameter, UserType.class))
                .toList();

        paramsList.forEach(parameter -> {
            UserType userType = parameter.getAnnotation(UserType.class);
            Optional<StaticUser> user = Optional.empty();
            StopWatch stopWatch = StopWatch.createStarted();

            while (user.isEmpty() && stopWatch.getTime(TimeUnit.SECONDS) < 15) {
                user = Optional.ofNullable(getUserFromQueueByUserType(userType.value()).poll());
            }
            user.ifPresentOrElse(
                    u -> {
                        ((Map<UserType, StaticUser>) context.getStore(NAMESPACE)
                                .getOrComputeIfAbsent(
                                        context.getUniqueId(),
                                        key -> new HashMap<>()
                                )).put(userType, u);
                    },
                    () -> {
                        throw new IllegalStateException("Can't find user after 15 sec");
                    }
            );
        });
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Map<UserType, StaticUser> map = context.getStore(NAMESPACE).get(context.getUniqueId(), Map.class);
        for (Map.Entry<UserType, StaticUser> e : map.entrySet()) {
            getUserFromQueueByUserType(e.getKey().value()).add(e.getValue());
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(StaticUser.class)
                && AnnotationSupport.isAnnotated(parameterContext.getParameter(), UserType.class);
    }

    @Override
    public StaticUser resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (StaticUser) extensionContext
                .getStore(NAMESPACE)
                .get(extensionContext.getUniqueId(), Map.class)
                .get(AnnotationSupport.findAnnotation(parameterContext.getParameter(), UserType.class).get());
    }

    private Queue<StaticUser> getUserFromQueueByUserType(UserType.Type userType) {
        return switch (userType) {
            case EMPTY -> EMPTY_USERS;
            case WITH_FRIEND -> WITH_FRIEND_USERS;
            case WITH_INCOME_REQUEST -> WITH_INCOME_REQUEST_USERS;
            case WITH_OUTCOME_REQUEST -> WITH_OUTCOME_REQUEST_USERS;
        };
    }
}
