const logError = error => {
  console.error(error);
};

const checkResponse = response => {
  if (response.status === 401) {
    throw new Error('Der Login ist fehlgeschlagen!');
  } else if (response.status === 403) {
    throw new Error('Der Zugriff wurde vom Server verweigert!');
  } else if (response.status !== 200) {
    throw new Error('Ein Fehler ist aufgetreten!');
  }
};

export { logError, checkResponse };
