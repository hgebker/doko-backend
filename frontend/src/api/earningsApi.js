import axios from 'axios';
import { apiUtils } from 'services/utils';

const getAllEarnings = async () => {
  const endpoint = '/earnings';
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

const createEarning = async newEarning => {
  const endpoint = `/earnings`;
  const requestConfig = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  try {
    await axios.post(endpoint, newEarning, requestConfig);
    return newEarning;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const updateEarning = async earningToUpdate => {
  const endpoint = `/earnings`;
  const requestConfig = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  try {
    await axios.put(endpoint, earningToUpdate, requestConfig);
    return earningToUpdate;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const deleteEarning = async kind => {
  const endpoint = `/earnings/${kind}`;

  try {
    await axios.delete(endpoint);
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

export { getAllEarnings, createEarning, updateEarning, deleteEarning };
