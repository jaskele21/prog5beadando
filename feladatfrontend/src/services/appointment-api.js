export const appointmentPost = async (createAppointment, key) => {
  try {
    const response = await fetch('http://localhost:8080/appointment', {
      method: 'POST',
      headers: {
        'x-api-key': key,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(createAppointment),
    })
    console.log('OK')
    return response.json()
  } catch (e) {
    console.log(e)
  }
}
