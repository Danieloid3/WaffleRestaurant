document.addEventListener('DOMContentLoaded', function () {
    // Animación inicial del formulario
    const signupForm = document.querySelector('section');
    signupForm.style.opacity = 0.8;

    setTimeout(() => {
        signupForm.style.transition = 'opacity 0.3s ease-in-out';
        signupForm.style.opacity = 1;
    }, 500);

    // Referencias a elementos del formulario
    const form = document.querySelector('form');
    const nombreInput = document.querySelector('input[name="nombre"]');
    const apellidoInput = document.querySelector('input[name="apellido"]');
    const emailInput = document.querySelector('input[type="email"]');
    const passwordInput = document.querySelector('input[type="password"][name="password"]');
    const confirmPasswordInput = document.querySelector('input[type="password"]:not([name="password"])');
    const submitButton = document.querySelector('button[type="submit"]');

    // Crear elementos para mostrar errores
    const errorNombreElement = document.createElement('div');
    errorNombreElement.className = 'field-error';
    errorNombreElement.style.display = 'none';
    errorNombreElement.textContent = 'El nombre debe tener al menos 2 caracteres';

    const errorApellidoElement = document.createElement('div');
    errorApellidoElement.className = 'field-error';
    errorApellidoElement.style.display = 'none';
    errorApellidoElement.textContent = 'El apellido debe tener al menos 2 caracteres';

    const errorEmailElement = document.createElement('div');
    errorEmailElement.className = 'field-error';
    errorEmailElement.style.display = 'none';
    errorEmailElement.textContent = 'Por favor ingresa un correo electrónico válido';

    const errorPasswordElement = document.createElement('div');
    errorPasswordElement.className = 'field-error';
    errorPasswordElement.style.display = 'none';
    errorPasswordElement.textContent = 'La contraseña debe tener al menos 6 caracteres';

    const errorConfirmPasswordElement = document.createElement('div');
    errorConfirmPasswordElement.className = 'field-error';
    errorConfirmPasswordElement.style.display = 'none';
    errorConfirmPasswordElement.textContent = 'Las contraseñas no coinciden';

    // Insertar elementos de error después de cada inputbox
    nombreInput.closest('.inputbox').after(errorNombreElement);
    apellidoInput.closest('.inputbox').after(errorApellidoElement);
    emailInput.closest('.inputbox').after(errorEmailElement);
    passwordInput.closest('.inputbox').after(errorPasswordElement);
    confirmPasswordInput.closest('.inputbox').after(errorConfirmPasswordElement);


    // Función para validar la longitud mínima
    function validateMinLength(input, minLength, errorElement) {
        if (input.value.length > 0 && input.value.length < minLength) {
            errorElement.style.display = 'block';
            input.closest('.inputbox').classList.add('error');
            return false;
        } else {
            errorElement.style.display = 'none';
            input.closest('.inputbox').classList.remove('error');
            return true;
        }
    }

    // Función para validar que las contraseñas coincidan
    function validatePasswords() {
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        // Primero validar longitud de la contraseña
        const isPasswordValid = validateMinLength(passwordInput, 6, errorPasswordElement);

        // Luego validar que coincidan
        if (confirmPassword && password !== confirmPassword) {
            errorConfirmPasswordElement.style.display = 'block';
            confirmPasswordInput.closest('.inputbox').classList.add('error');
            return false;
        } else {
            errorConfirmPasswordElement.style.display = 'none';
            confirmPasswordInput.closest('.inputbox').classList.remove('error');
            return isPasswordValid;
        }
    }

    function validateEmail() {
        if (emailInput.value && !emailInput.checkValidity()) {
            errorEmailElement.style.display = 'block';
            emailInput.closest('.inputbox').classList.add('error');
            return false;
        } else {
            errorEmailElement.style.display = 'none';
            emailInput.closest('.inputbox').classList.remove('error');
            return true;
        }
    }

    // Eventos para validar nombre
    nombreInput.addEventListener('input', function() {
        validateMinLength(this, 2, errorNombreElement);
    });

    nombreInput.addEventListener('blur', function() {
        validateMinLength(this, 2, errorNombreElement);
    });

    // Eventos para validar apellido
    apellidoInput.addEventListener('input', function() {
        validateMinLength(this, 2, errorApellidoElement);
    });

    apellidoInput.addEventListener('blur', function() {
        validateMinLength(this, 2, errorApellidoElement);
    });

    emailInput.addEventListener('input', validateEmail);
    emailInput.addEventListener('blur', validateEmail);


    // Eventos para validar contraseña
    passwordInput.addEventListener('input', function() {
        validateMinLength(this, 6, errorPasswordElement);
        if (confirmPasswordInput.value) {
            validatePasswords();
        }
    });

    passwordInput.addEventListener('blur', function() {
        validateMinLength(this, 6, errorPasswordElement);
    });

    // Eventos para validar confirmación de contraseña
    confirmPasswordInput.addEventListener('input', validatePasswords);
    confirmPasswordInput.addEventListener('blur', validatePasswords);

    form.addEventListener('submit', function(event) {
        // Validar todos los campos
        const isNombreValid = validateMinLength(nombreInput, 2, errorNombreElement);
        const isApellidoValid = validateMinLength(apellidoInput, 2, errorApellidoElement);
        const isEmailValid = validateEmail();
        const isPasswordValid = validateMinLength(passwordInput, 6, errorPasswordElement);
        const arePasswordsMatching = validatePasswords();

        if (!isNombreValid || !isApellidoValid || !isEmailValid || !isPasswordValid || !arePasswordsMatching) {
            event.preventDefault();
            signupForm.classList.add('shake');

            setTimeout(() => {
                signupForm.classList.remove('shake');
            }, 1000);
            return false;
        }

        // Si todo está correcto, el formulario se envía normalmente
        return true;
    });

    // Añadir clase de animación de sacudida al formulario
    if (!document.styleSheets[0].cssRules[0].selectorText.includes('.shake')) {
        const style = document.createElement('style');
        style.innerHTML = `
            @keyframes shake {
                0%, 100% { transform: translateX(0); }
                10%, 30%, 50%, 70%, 90% { transform: translateX(-5px); }
                20%, 40%, 60%, 80% { transform: translateX(5px); }
            }
            .shake {
                animation: shake 0.5s ease-in-out;
            }
        `;
        document.head.appendChild(style);
    }
});