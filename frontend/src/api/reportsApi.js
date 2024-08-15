import axios from 'axios';
import { apiUtils } from 'services/utils';

const getReportForSemester = async semester => {
  const endpoint = semester !== 'gesamt' ? `/reports/semester?semester=${semester}` : '/reports/semester';
  const requestConfig = {
    headers: {
      Accept: 'application/json'
    }
  };

  try {
    const report = await axios.get(endpoint, requestConfig);

    return report.data;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

const getCashReport = async () => {
  const endpoint = '/reports/cash';
  const requestConfig = {
    headers: {
      Accept: 'application/json'
    }
  };

  try {
    const report = await axios.get(endpoint, requestConfig);

    return report.data;
  } catch (error) {
    apiUtils.logError(error);
    throw error;
  }
};

export { getReportForSemester, getCashReport };
