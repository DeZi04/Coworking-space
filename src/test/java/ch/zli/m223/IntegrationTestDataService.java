package ch.zli.m223;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Member;
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
    firstworkplace.setOcupation(null);
    entityManager.persist(firstworkplace);

    var secondworkplace = new Workplace();
    secondworkplace.setOcupation(null);
    entityManager.persist(secondworkplace);

    var thirdworkplace = new Workplace();
    thirdworkplace.setOcupation(null);
    entityManager.persist(thirdworkplace);

    // Members
    var firstMember = new Member();
    firstMember.setFirstname("Helena");
    firstMember.setLastname("Hugentobler");
    firstMember.setEmail("helena.hugentobler@tbz.ch");
    firstMember.setPassword("ZLI1234");
    entityManager.persist(firstMember);

    var secondMember = new Member();
    secondMember.setFirstname("Mike");
    secondMember.setLastname("Brook");
    secondMember.setEmail("mike.brook@tbz.ch");
    secondMember.setPassword("iLoveBrook");
    entityManager.persist(secondMember);

    var thirdMember = new Member();
    thirdMember.setFirstname("Gundula");
    thirdMember.setLastname("Friedmut");
    thirdMember.setEmail("gundula.friedmut@tbz.ch");
    thirdMember.setPassword("lovepeace");
    entityManager.persist(thirdMember);

    // Entries
    var firstEntry = new Entry();
    firstEntry.setStatus("pending");
    firstEntry.setDate(LocalDateTime.now().plusDays(3));
    firstEntry.setWorkplace(firstworkplace);
    firstEntry.setMember(firstMember);
    entityManager.persist(firstEntry);


    var secondEntry = new Entry();
    secondEntry.setStatus("accepted");
    secondEntry.setDate(LocalDateTime.now().plusDays(2));
    secondEntry.setWorkplace(secondworkplace);
    secondEntry.setMember(secondMember);
    entityManager.persist(secondEntry);

    var thirdEntry = new Entry();
    thirdEntry.setStatus("declined");
    thirdEntry.setDate(LocalDateTime.now().plusDays(1));
    thirdEntry.setWorkplace(thirdworkplace);
    thirdEntry.setMember(thirdMember);
    entityManager.persist(thirdEntry);
  }
}
