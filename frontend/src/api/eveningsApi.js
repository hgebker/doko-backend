import axios from 'axios';
import { apiUtils } from 'services/utils';

const listEvenings = async semester => {
  const endpoint = semester !== 'gesamt' ? `/evenings?semester=${semester}` : '/evenings';
  const requestConfig = {
    headers: {
      Accept: 'application/json'
    }
  };

  try {
    const response = await axios.get(endpoint, requestConfig);

    return response.data;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const getEvening = async date => {
  const endpoint = `/evenings/${date}`;
  const requestConfig = {
    headers: {
      Accept: 'application/json'
    }
  };

  try {
    const response = await axios.get(endpoint, requestConfig);

    return response.data.listEvenings.items;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const createEvening = async newEvening => {
  const endpoint = `/evenings`;
  const requestConfig = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  try {
    await axios.post(endpoint, newEvening, requestConfig);
    return newEvening;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const updateEvening = async eveningToUpdate => {
  const endpoint = `/evenings`;
  const requestConfig = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  try {
    await axios.put(endpoint, eveningToUpdate, requestConfig);
    return eveningToUpdate;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const deleteEvening = async date => {
  const endpoint = `/evenings/${date}`;

  try {
    await axios.delete(endpoint);
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

export { listEvenings, getEvening, createEvening, updateEvening, deleteEvening };
