async function main() {
	passwordShowUp()
	getLocationInfo()
	validateUsername()
	validateEmail()
	save()
}

async function save() {
	document.getElementById('form').addEventListener('submit', (e) => {
		e.preventDefault() // to avoid header-contentType Error

		if (checkInputs()) {
			getExactUrlToSaveUserWithAuthority()

		}
	})
}

async function getExactUrlToSaveUserWithAuthority() {
	if (window.location.href == "http://localhost:8080/user/self/registeration") {
		register(setUser(), "/user/self/registeration", "", document.getElementById('btn'))
		document.getElementById("successUserRole").style.display = "block"
		document.getElementById("successUserRole").innerHTML = "Congratulations, New Account has been successfully created. Click here to redirect to Login Page"
	} else if (window.location.href == "http://localhost:8080/register/admin/user") {
		register(setUser(), "/register/admin/user", "", document.getElementById('btn'))
		document.getElementById("successAdminRole").style.display = "block"
			document.getElementById("successAdminRole").innerHTML = "Congratulations, New Account has been successfully created. Click here to redirect to Dashboard"
	}
}
async function validateUsername() {
	document.getElementById('username').addEventListener('blur', () => {
		if (checkInputs()) {
			register(setUser(), "/validate/username", "username is already token", document.getElementById('username'))
		}
	})
}

async function validateEmail() {
	document.getElementById('email').addEventListener('blur', () => {
		if (checkInputs()) {
			register(setUser(), "/validate/email", "email is already token", document.getElementById('email'))

		}
	})
}

function setUser() {

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
			zipCode: zipCode.value,
			city: document.getElementById('city').innerText,
			state: document.getElementById('state').innerText
		}
	}


	return user

}



function register(user, url, errorMessage, element) {

	fetch(url, {
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
				// user exists
				setErrorFor(element, errorMessage)
				element.select()
				element.focus()
			} else {
				setSuccessFor(element)
			}

		})
}







async function passwordShowUp() {
	let eyeIcons = document.querySelectorAll('.fa-eye')

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


function checkInputs() {
	let firstName = document.getElementById('firstName')
	let lastName = document.getElementById('lastName')
	let username = document.getElementById('username');
	let password = document.getElementById('password');
	let confirmPassword = document.getElementById('confirmPassword');
	let dateOfBirth = document.getElementById('dateOfBirth')
	let email = document.getElementById('email')
	let consecration = document.getElementById('consecration')
	let addressLine = document.getElementById('addressLine')

	if (firstName.value.trim() === '') {
		setErrorFor(firstName, "First Name can't be blank")
		firstName.focus()
		return false
	} else {
		setSuccessFor(firstName)
	}


	if (lastName.value.trim() === '') {
		setErrorFor(lastName, "Last Name can't be blank")
		lastName.focus()
		return false
	} else {
		setSuccessFor(lastName)
	}

	if (dateOfBirth.value.trim() === '') {
		setErrorFor(dateOfBirth, "Date Of Birth can't be blank")
		dateOfBirth.focus()
		return false
	} else {
		setSuccessFor(dateOfBirth)
	}


	if (addressLine.value.trim() === '') {
		setErrorFor(addressLine, "Address can't be blank")
		addressLine.focus()
		return false
	} else {
		setSuccessFor(addressLine)
	}


	if (password.value === '') {
		setErrorFor(password, "password can't be blank")
		password.focus()
		password.select()
		return false
	} else {
		setSuccessFor(password)
	}

	if (confirmPassword.value === '') {
		setErrorFor(confirmPassword, "password can't be blank")
		confirmPassword.focus()
		confirmPassword.select()
		return false
	} else if (password.value !== confirmPassword.value) {
		setErrorFor(confirmPassword, "password doesn't match")
		return false
	} else {
		setSuccessFor(confirmPassword)
	}


	if (consecration.value === 'NONE') {
		setErrorFor(consecration, 'Select your consecration')
		consecration.focus()
		return false
	} else {
		setSuccessFor(consecration)
	}

	if (email.value.trim() === '') {
		setErrorFor(email, "Email can't be blank")
		email.focus()
		return false
	} else {
		setSuccessFor(email)
	}

	if (username.value.trim() === '') {
		setErrorFor(username, "Username can't be blank")
		username.focus()
		return false
	} else {
		setSuccessFor(username)
	}

	return true

}
function getLocationInfo() {
	let zipCode = document.getElementById('zipCode')

	zipCode.addEventListener('blur', () => {
		fetch(`http://api.zippopotam.us/us/${zipCode.value}`)
			.then(response => {
				if (response.status != 200) {
					setErrorFor(zipCode, "Invalid Zipcode")
					throw Error(response.statusText)
				} else {
					setSuccessFor(zipCode)
					return response.json()
				}
			})
			.then(data => {

				document.getElementById('city').innerText = data.places.at(0)["place name"]
				document.getElementById('state').innerText = data.places.at(0)["state abbreviation"]

			})
	})

}

main();
document.getElementById("success").style.display = "hidden"