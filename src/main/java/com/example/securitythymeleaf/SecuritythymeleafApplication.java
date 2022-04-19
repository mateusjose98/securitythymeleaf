package com.example.securitythymeleaf;

import com.example.securitythymeleaf.entities.Perfil;
import com.example.securitythymeleaf.entities.Usuario;
import com.example.securitythymeleaf.repositories.PerfilRepository;
import com.example.securitythymeleaf.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class SecuritythymeleafApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecuritythymeleafApplication.class, args);
    }

    private UsuarioRepository usuarioRepository;
    private PerfilRepository perfilRepository;
    private PasswordEncoder encoder;

    public SecuritythymeleafApplication(UsuarioRepository usuarioRepository,
                                        PerfilRepository perfilRepository,
                                        PasswordEncoder encoder){
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.encoder = encoder;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Salvando as authorities");
        Perfil perfilAdm = new Perfil();
        perfilAdm.setId(1);
        perfilAdm.setPerfil("ADM");

        Perfil comum = new Perfil();
        comum.setId(2);
        comum.setPerfil("COMUM");

        perfilRepository.saveAndFlush(comum);
        perfilRepository.saveAndFlush(perfilAdm);


        Usuario usuarioComum = new Usuario();

        String senhaUsuarioComum = "comum123";
        String senhaBcript = encoder.encode(senhaUsuarioComum);

        usuarioComum.setEmail("usuariocomum@gmail.com");
        usuarioComum.setDataRegistro(LocalDate.now());
        usuarioComum.setNome("usuariocomum");
        usuarioComum.setUsername("usuarioComum");
        usuarioComum.setPassword(senhaBcript);
        System.out.println("Salvando usuário usuarioComum com senha comum123 - " + senhaBcript);
        usuarioComum.adicionarPerfil(perfilRepository.findById(2).get());
        usuarioRepository.save(usuarioComum);

        Usuario usuarioAdm = new Usuario();

        String senhaUsuarioAdm = "adm123";
        String senhaBcriptAdm = encoder.encode(senhaUsuarioAdm);

        usuarioAdm.setEmail("usuarioadm@gmail.com");
        usuarioAdm.setDataRegistro(LocalDate.now());
        usuarioAdm.setNome("usuarioAdm");
        usuarioAdm.setUsername("usuarioAdm");
        usuarioAdm.setPassword(senhaBcriptAdm);
        usuarioAdm.adicionarPerfil(perfilRepository.findById(1).get());
        System.out.println("Salvando usuário usuarioAdm com senha adm123 " + senhaBcriptAdm);
        usuarioRepository.save(usuarioAdm);







    }
}
