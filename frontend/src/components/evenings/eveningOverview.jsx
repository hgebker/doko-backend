import { useState, useEffect } from 'react';

import { eveningsAPI } from 'api';
import { useToasts } from 'components/HOC/withToasts';
import { useModal } from 'components/HOC/withModal';
import useEvenings from './useEvenings';
import AddEveningForm from './eveningForm';
import EveningsListView from './eveningsListView';
import EveningsTableView from './eveningsTableView';

export const VIEWS = [
  { label: 'Anzeigen als', type: 'header' },
  {
    label: 'Tabellenansicht',
    value: 'table',
    rightIcon: {
      category: 'utility',
      name: 'table'
    }
  },
  {
    label: 'Listenansicht',
    value: 'list',
    rightIcon: {
      category: 'utility',
      name: 'side_list'
    }
  }
];

const EveningOverview = () => {
  const [selectedSemester, setSelectedSemester] = useState({ id: 'gesamt', label: 'Gesamt' });
  const [evenings, loadEvenings, spinner, setLoading] = useEvenings(selectedSemester);
  const [selectedView, setSelectedView] = useState(VIEWS[1]);
  const [selectedEvening, setSelectedEvening] = useState(null);

  const [toast, showToast] = useToasts();
  const [modal, openModal] = useModal();

  useEffect(() => {
    setSelectedEvening(evenings[0]);
  }, [evenings]);

  const createEvening = async newEvening => {
    setLoading(true);
    try {
      await eveningsAPI.createEvening(newEvening);
      loadEvenings();

      showToast('Erfolg!', 'Der Abend wurde erfolgreich gespeichert.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Der Abend konnte nicht gespeichert werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const updateEvening = async eveningToUpdate => {
    setLoading(true);
    try {
      await eveningsAPI.updateEvening(eveningToUpdate);
      loadEvenings();

      showToast('Erfolg!', 'Der Abend wurde erfolgreich aktualisiert.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Der Abend konnte nicht aktualisiert werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const deleteEvening = async date => {
    setLoading(true);
    try {
      await eveningsAPI.deleteEvening(date);
      loadEvenings();

      showToast('Erfolg!', 'Der Abend wurde erfolgreich gelöscht.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Der Abend konnte nicht gelöscht werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const handleSemesterSelected = selectedSemester => {
    setSelectedSemester(selectedSemester);
  };

  const openFormModal = eveningToUpdate => {
    openModal({
      heading: `Abend ${eveningToUpdate ? 'bearbeiten' : 'anlegen'}`,
      buttons: [
        {
          label: 'Abbrechen'
        },
        {
          label: 'Speichern',
          variant: 'brand',
          action: childState => {
            if (eveningToUpdate) {
              updateEvening(childState.item);
            } else {
              createEvening(childState.item);
            }
          }
        }
      ],
      child: {
        type: AddEveningForm,
        attributes: {
          presetEvening: eveningToUpdate
            ? {
                date: eveningToUpdate.date,
                semester: eveningToUpdate.semester,
                jan: eveningToUpdate.jan,
                ole: eveningToUpdate.ole,
                tim: eveningToUpdate.tim,
                louisa: eveningToUpdate.louisa,
                hannes: eveningToUpdate.hannes
              }
            : null
        }
      },
      options: {
        dismissOnClickOutside: false
      }
    });
  };

  const getContent = () => {
    switch (selectedView.value) {
      case 'table':
        return (
          <EveningsTableView
            evenings={evenings}
            onOpenModal={openFormModal}
            onRefresh={loadEvenings}
            selectedSemester={selectedSemester}
            onSemesterSelected={handleSemesterSelected}
            selectedView={selectedView}
            onViewChange={setSelectedView}
            onDelete={deleteEvening}
            selectedEvening={selectedEvening}
            onEveningSelected={setSelectedEvening}
          />
        );

      default:
        return (
          <EveningsListView
            evenings={evenings}
            onOpenModal={openFormModal}
            onRefresh={loadEvenings}
            selectedSemester={selectedSemester}
            onSemesterSelected={handleSemesterSelected}
            selectedView={selectedView}
            onViewChange={setSelectedView}
            onDelete={deleteEvening}
            selectedEvening={selectedEvening}
            onEveningSelected={setSelectedEvening}
          />
        );
    }
  };

  return (
    <>
      {toast}
      {modal}
      {spinner}

      {getContent()}
    </>
  );
};

export default EveningOverview;
