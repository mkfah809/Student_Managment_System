let username = document.getElementById('username')
username.addEventListener('blur', (e) => {
	zipCode.addEventListener('blur', () => {
		fetch(`/new/register/username/${username.value}`)
			.then(response => {
				if (response.status != 200) {
					console.log("Error")
					throw Error(response.statusText)
				} else {
					console.log("OK")
					setSuccessFor(zipCode)
					return response.json()
				}
			})
			.then(data => {

				console.log("data", data)

			})
	})
})

async function main() {
	passwordShowUp()
	getLocationInfo()

	document.getElementById('form').addEventListener('submit', (e) => {
		e.preventDefault();
		if (checkInputs()) {
			getLocationInfo();
			let user = register()
		}

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
				// user exists
				setErrorFor(username, 'username already exists')
				username.focus()
				username.select()
			} else {
				document.getElementById("success").innerHTML = "Congratulations, New Account has been successfully created."
			}

		})

	return user

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
		setErrorFor(firstName, 'First Name cannot be blank')
		firstName.focus()
		return false
	} else {
		setSuccessFor(firstName)
	}


	if (lastName.value.trim() === '') {
		setErrorFor(lastName, 'Last Name cannot be blank')
		firstName.focus()
		return false
	} else {
		setSuccessFor(lastName)
	}

	if (dateOfBirth.value.trim() === '') {
		setErrorFor(dateOfBirth, 'Date Of Birth cannot be blank')
		dateOfBirth.focus()
		return false
	}

	else {
		setSuccessFor(dateOfBirth)
	}

	if (email.value.trim() === '') {
		setErrorFor(email, 'Email cannot be blank')
		email.focus()
		return false
	} else {
		setSuccessFor(email)
	}

	if (addressLine.value.trim() === '') {
		setErrorFor(addressLine, 'Address cannot be blank')
		addressLine.focus()
		return false
	} else {
		setSuccessFor(addressLine)
	}


	if (password.value === '') {
		setErrorFor(password, 'password cannot be blank')
		password.focus()
		password.select()
		return false
	} else {
		setSuccessFor(password)
	}

	if (confirmPassword.value === '') {
		setErrorFor(confirmPassword, 'password cannot be blank')
		confirmPassword.focus()
		confirmPassword.select()
		return false
	} else if (password.value !== confirmPassword.value) {
		setErrorFor(confirmPassword, 'password does not match')
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

	if (username.value.trim() === '') {
		setErrorFor(username, 'Username cannot be blank')
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
					setErrorFor(zipCode, "Invalid Zipcode, please try again")
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




main()