package ch.zli.m223;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Member;
import ch.zli.m223.model.RoleEnum;
import ch.zli.m223.model.StatusEnume;
import ch.zli.m223.model.Workplace;
import ch.zli.m223.model.Entry;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("test")
@ApplicationScoped
public class IntegrationTestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  void generateTestData(@Observes StartupEvent event) {

    // Workpaces
    var firstworkplace = new Workplace();
    firstworkplace.setOcupation(true);
    entityManager.persist(firstworkplace);

    var secondworkplace = new Workplace();
    secondworkplace.setOcupation(true);
    entityManager.persist(secondworkplace);

    var thirdworkplace = new Workplace();
    thirdworkplace.setOcupation(false);
    entityManager.persist(thirdworkplace);

    // Members
    var firstMember = new Member();
    firstMember.setFirstname("Helena");
    firstMember.setLastname("Hugentobler");
    firstMember.setEmail("helena.hugentobler@tbz.ch");
    firstMember.setPassword("ZLI1234");
    firstMember.setRole(RoleEnum.MEMBER);
    entityManager.persist(firstMember);

    var secondMember = new Member();
    secondMember.setFirstname("Mike");
    secondMember.setLastname("Brook");
    secondMember.setEmail("mike.brook@tbz.ch");
    secondMember.setPassword("iLoveBrook");
    secondMember.setRole(RoleEnum.MEMBER);
    entityManager.persist(secondMember);

    var thirdMember = new Member();
    thirdMember.setFirstname("Gundula");
    thirdMember.setLastname("Friedmut");
    thirdMember.setEmail("gundula.friedmut@tbz.ch");
    thirdMember.setPassword("lovepeace");
    thirdMember.setRole(RoleEnum.ADMIN);
    entityManager.persist(thirdMember);

    // Entries
    var firstEntry = new Entry();
    firstEntry.setStatus(StatusEnume.PENDING);
    firstEntry.setDuration(0.5);
    firstEntry.setDate(LocalDateTime.now().plusDays(3));
    firstEntry.setWorkplace(firstworkplace);
    firstEntry.setMember(firstMember);
    entityManager.persist(firstEntry);


    var secondEntry = new Entry();
    secondEntry.setStatus(StatusEnume.ACCEPTED);
    secondEntry.setDuration(1);
    secondEntry.setDate(LocalDateTime.now().plusDays(2));
    secondEntry.setWorkplace(secondworkplace);
    secondEntry.setMember(secondMember);
    entityManager.persist(secondEntry);

    var thirdEntry = new Entry();
    thirdEntry.setStatus(StatusEnume.DECLINED);
    thirdEntry.setDuration(1);
    thirdEntry.setDate(LocalDateTime.now().plusDays(1));
    thirdEntry.setWorkplace(thirdworkplace);
    thirdEntry.setMember(thirdMember);
    entityManager.persist(thirdEntry);
  }
}
