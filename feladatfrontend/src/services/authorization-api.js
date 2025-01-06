export const loginApi = async (password, phoneNumber) => {
  const response = await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ password, phoneNumber }),
  })

  return response.json()
}
export const authMeApi = async (key) => {
  const response = await fetch('http://localhost:8080/auth/me', {
    method: 'GET',
    headers: {
      'x-api-key': key,
      'Content-Type': 'application/json',
    },
  })

  return response.json()
}
export const logoutApi = async (key) => {
  await fetch('http://localhost:8080/auth/logout', {
    method: 'POST',
    headers: {
      'x-api-key': key,
      'Content-Type': 'application/json',
    },
  })
}
export const signInApi = async (password, phoneNumber, name) => {
  const response = await fetch('http://localhost:8080/auth/sign', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ password, name, phoneNumber }),
  })
  return response.json()
}
