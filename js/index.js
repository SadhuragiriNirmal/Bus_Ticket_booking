document.getElementById('loginButton').addEventListener('click', ()=> {
    let ov = document.getElementById('overlay')
    ov.style.display='flex'
    ov.style.alignItems='center'
    ov.style.justifyContent='center'
});

document.getElementById('closeButton').addEventListener('click', ()=> {
    document.getElementById('overlay')
    .style.display='none'
});

document.getElementById('signUpButton').addEventListener('click', function() {
    let ov = document.getElementById('signUpOverlay')
    ov.style.display='flex'
    ov.style.alignItems='center'
    ov.justifyContent='center'
});

document.getElementById('closeSignUpButton').addEventListener('click', function() {
    document.getElementById('signUpOverlay').style.display = 'none'
});


document.getElementById('closeMessageButton').addEventListener('click', function () {
  document.getElementById('messageOverlay').style.display = 'none';
});

document.getElementById('signup').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    
  
    const formData = {
  
      uname: document.getElementById('newUsername').value,
      upassword: document.getElementById('newPassword').value,
      uemail: document.getElementById('email').value
  
    };

    const loadingSpinner = document.getElementById('loadingSpinner');
    loadingSpinner.classList.remove('hidden');
  
    console.log('Form Data:', formData); // Log the FormData object to see its contents
  
    fetch('http://localhost:8080/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(response => {
      console.log('Response from server:', response);  
      loadingSpinner.classList.add('hidden');
      document.getElementById('signUpOverlay').style.display = 'none';
      let messageOverlay = document.getElementById('messageOverlay');
      document.getElementById('disname').innerHTML = `<b>${response.data.uname}</b>`
      messageOverlay.style.display = 'flex';
      messageOverlay.style.alignItems = 'center';
      messageOverlay.style.justifyContent = 'center';
      document.getElementById('signup').reset();
    })
    .catch(error => {
      console.error('There was a problem with the fetch operation:', error);
    });
    
  });


  document.getElementById('loginform').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the default form submission

    const loginData = {
        emailid: document.getElementById('emailid').value,
        password: document.getElementById('password').value
    };
    console.log('Login Data:', loginData); // Log the loginData object to see its contents

    const loadingSpinnerlog = document.getElementById('loadingSpinnerlog');
    loadingSpinnerlog.classList.remove('hid');

    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        
        console.log('Response from server:', data);
        loadingSpinnerlog.classList.add('hid');
        window.location.href = 'home.html'; // Redirect to home.html on successful login
    })
    .catch(error => {
      
        console.error('There was a problem with the fetch operation:', error);
    });
});