import { useCallback, useEffect, useState } from 'react';
import { reportsAPI } from 'api';
import { useSpinner } from 'components/HOC/withSpinner';

const useReport = selectedSemester => {
  const [report, setReport] = useState(null);
  const [spinner, setLoading] = useSpinner();

  const loadReport = useCallback(async () => {
    setLoading(true);
    try {
      const response = await reportsAPI.getReportForSemester(selectedSemester.id);
      setReport(response);
    } catch (error) {
      setReport(null);
    } finally {
      setLoading(false);
    }
  }, [selectedSemester, setLoading]);

  useEffect(() => {
    loadReport();
  }, [selectedSemester, loadReport]);

  return [report, loadReport, spinner, setLoading];
};

export default useReport;
