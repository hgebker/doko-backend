import { useCallback, useEffect, useState } from 'react';
import { earningsAPI } from 'api';
import { useSpinner } from 'components/HOC/withSpinner';

export default function useEarnings() {
  const [earnings, setEarnings] = useState([]);
  const [spinner, setLoading] = useSpinner();

  const loadEarnings = useCallback(async () => {
    setLoading(true);
    try {
      const response = await earningsAPI.getAllEarnings();
      setEarnings(response._embedded.earningList);
    } catch (error) {
      setEarnings([]);
    } finally {
      setLoading(false);
    }
  }, [setLoading]);

  useEffect(() => {
    loadEarnings();
  }, [loadEarnings]);

  return [earnings, loadEarnings, spinner, setLoading];
}
