
const firstName = document.getElementById('firstName')
const lastName = document.getElementById('lastName')
const username = document.getElementById('username');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const dateOfBirth = document.getElementById('dateOfBirth')
const email = document.getElementById('email')
const consecration = document.getElementById('consecration')
const addressLine = document.getElementById('addressLine')
const zipCode = document.getElementById('zipCode')
const form = document.getElementById('form');
const eyeIcons = document.querySelectorAll('.fa-eye')



async function main() {
	form.addEventListener('submit', (e) => {
		e.preventDefault();

		register()

	})
}

function register() {

	let user = {
		firstName: firstName.value,
		lastName: lastName.value,
		username: username.value,
		password: password.value,
		dateOfBirth: dateOfBirth.value,
		email: email.value,
		consecration: consecration.value,
		address: {
			addressLine: addressLine.value,
			zibCode: zipCode.value
		}
	}


	fetch("/new/register", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			'X-CSRF-TOKEN': document.getElementById('csrf').value
		},
		body: JSON.stringify(user)
	})
		.then((response) => response.json())
		.then((data) => {
			if (data === true) {
				// if emp not new
				setErrorFor(username, 'username already exists')
				username.focus()
				username.select()
			} else {
				document.getElementById("success").style.display = "block"
				document.getElementById("success").innerHTML = "Congratulations, New Account has been successfully created."
			}

		})

}

async function passwordShowUp() {
	eyeIcons.forEach((eyeIcon) => {
		eyeIcon.addEventListener('click', () => {

			if (eyeIcon.classList.contains('fa-eye')) {
				eyeIcon.classList.replace('fa-eye', 'fa-eye-slash')


				if (eyeIcon.getAttribute('id') === 'passwordEyeIcon') {
					password.type = 'text'
				} else {
					confirmPassword.type = 'text'
				}

			} else {
				eyeIcon.classList.replace('fa-eye-slash', 'fa-eye')
				if (eyeIcon.getAttribute('id') === 'passwordEyeIcon') {
					password.type = 'password'
				} else {
					confirmPassword.type = 'password'
				}
			}
		})
	})
}



async function setErrorFor(input, message) {
	const formControl = input.parentElement; //.form-control
	const small = formControl.querySelector('small')
	small.innerText = message;
	formControl.className = 'form-control error'
}

async function setSuccessFor(input) {
	const formControl = input.parentElement; //.form-control
	formControl.className = 'form-control success'
}

function isPassword(password) {
	return new RegExp('[0-9]').test(password);
}




passwordShowUp()
main()
