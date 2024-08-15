import { useCallback, useEffect, useState } from 'react';
import { expensesAPI } from 'api';
import { useSpinner } from 'components/HOC/withSpinner';

export default function useExpenses() {
  const [expenses, setExpenses] = useState([]);
  const [spinner, setLoading] = useSpinner();

  const loadExpenses = useCallback(async () => {
    setLoading(true);
    try {
      const response = await expensesAPI.getAllExpenses();
      setExpenses(response._embedded.expenseList);
    } catch (error) {
      setExpenses([]);
    } finally {
      setLoading(false);
    }
  }, [setLoading]);

  useEffect(() => {
    loadExpenses();
  }, [loadExpenses]);

  return [expenses, loadExpenses, spinner, setLoading];
}
