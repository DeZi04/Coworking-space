package ch.zli.m223.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.google.common.hash.Hashing;

import ch.zli.m223.model.Member;
import ch.zli.m223.model.RoleEnum;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class SessionService {

  @Inject
  MemberSercvice memberService;

  public Response authenticate(Member member) {
    Optional<Member> principal = memberService.findByEmail(member.getEmail());

    try {
      if (principal.isPresent() && principal.get().getPassword().equals(Hashing.sha512().hashString(member.getPassword(), StandardCharsets.UTF_8).toString())) {
        String token = Jwt
            .issuer("https://zli.example.com/")
            .upn(member.getEmail())
            .expiresIn(Duration.ofHours(24))
            .sign();
            if (member.getRole() == RoleEnum.ADMIN) {
              Jwt.groups(new HashSet<>(Arrays.asList( "Admin")));
            } else {
              Jwt.groups(new HashSet<>(Arrays.asList("User")));
            }
        return Response
            .ok(principal.get())
            .cookie(new NewCookie("CoWorkingSpace", token))
            .header("Authorization", "Bearer " + token)
            .build();
      }
    } catch (Exception e) {
      System.err.println("Couldn't validate password.");
    }

    return Response.status(Response.Status.FORBIDDEN).build();
  }
}
