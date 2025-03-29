package talle2.wafflerestaurant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import talle2.wafflerestaurant.Entities.Cliente;
import talle2.wafflerestaurant.Entities.Usuario;
import talle2.wafflerestaurant.Repositories.UsuarioRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> listAll() {
        return (List<Usuario>) repo.findAll();
    }


    public void save(Usuario usuario) {
        repo.save(usuario);
    }

    public Usuario get(long id) throws UsuarioNotFoundException {
        {
            Optional<Usuario> usuario = repo.findById(id);
            if (usuario.isPresent()) {
                return usuario.get();
            } else {
                throw new UsuarioNotFoundException("No se ha encontrado el usuario con id: " + id);//

            }
        }

    }

    public void delete(long id) throws UsuarioNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UsuarioNotFoundException("No se ha encontrado el usuario con id: " + id);
        } else {
            repo.deleteById(id);
        }
    }

     public boolean validarUsuario(String email, String password) {
         Optional<Usuario> usuario = repo.findByEmail(email);

         if (usuario.isPresent()) {
             if(usuario.get().getPassword().equals(password)){
                 return true;
             }
         }
         return false;
    }
}