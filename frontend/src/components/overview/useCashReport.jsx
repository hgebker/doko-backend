import { useEffect, useState } from 'react';
import { reportsAPI } from 'api';
import { useSpinner } from 'components/HOC/withSpinner';

const useCashReport = () => {
  const [report, setReport] = useState(null);
  const [spinner, setLoading] = useSpinner();

  useEffect(() => {
    const loadCashReport = async () => {
      setLoading(true);
      try {
        setReport(await reportsAPI.getCashReport());
      } catch (error) {
        setReport(null);
      } finally {
        setLoading(false);
      }
    };

    loadCashReport();
  }, [setLoading]);

  return [report, spinner];
};

export default useCashReport;
