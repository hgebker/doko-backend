import { useCallback, useEffect, useState } from 'react';
import { eveningsAPI } from 'api';
import { useSpinner } from 'components/HOC/withSpinner';

export const parseEveningDto = eveningDto => {
  return eveningDto.results.reduce((dict, result) => {
    dict[result.player] = result.value;
    return dict;
  }, eveningDto);
};

const useEvenings = selectedSemester => {
  const [evenings, setEvenings] = useState([]);
  const [spinner, setLoading] = useSpinner();

  const loadEvenings = useCallback(async () => {
    setLoading(true);
    try {
      const response = await eveningsAPI.listEvenings(selectedSemester.id);
      setEvenings(response._embedded.eveningDTOList.map(parseEveningDto));
    } catch (error) {
      setEvenings([]);
    } finally {
      setLoading(false);
    }
  }, [selectedSemester, setLoading]);

  useEffect(() => {
    loadEvenings();
  }, [selectedSemester, loadEvenings]);

  return [evenings, loadEvenings, spinner, setLoading];
};

export default useEvenings;
