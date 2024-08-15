import axios from 'axios';
import { apiUtils } from 'services/utils';

const getAllExpenses = async () => {
  const endpoint = '/expenses';
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

const createExpense = async newExpense => {
  const endpoint = `/expenses`;
  const requestConfig = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  try {
    await axios.post(endpoint, newExpense, requestConfig);
    return newExpense;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const updateExpense = async expenseToUpdate => {
  const endpoint = `/expenses`;
  const requestConfig = {
    headers: {
      'Content-Type': 'application/json'
    }
  };

  try {
    await axios.put(endpoint, expenseToUpdate, requestConfig);
    return expenseToUpdate;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const deleteExpense = async kind => {
  const endpoint = `/expenses/${kind}`;

  try {
    await axios.delete(endpoint);
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

export { getAllExpenses, createExpense, updateExpense, deleteExpense };
