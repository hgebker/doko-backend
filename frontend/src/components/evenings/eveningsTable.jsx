import DataTableColumn from '@salesforce/design-system-react/components/data-table/column';

import BaseTable from 'components/base/baseTable';
import BaseTableNumberCell from 'components/base/baseTableNumberCell';
import BaseTableSemesterCell from 'components/base/baseTableSemesterCell';

const COLUMNS = [
  <DataTableColumn key="date" label="Datum" property="date" />,
  <DataTableColumn key="semester" label="Semester" property="semester">
    <BaseTableSemesterCell />
  </DataTableColumn>,
  <DataTableColumn key="tim" label="Tim" property="tim">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="jan" label="Jan" property="jan">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="ole" label="Ole" property="ole">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="hannes" label="Hannes" property="hannes">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="louisa" label="Louisa" property="louisa">
    <BaseTableNumberCell />
  </DataTableColumn>
];

const ROW_ACTIONS = [
  {
    id: 'edit',
    label: 'Bearbeiten',
    value: 'edit'
  },
  {
    id: 'delete',
    label: 'Löschen',
    value: 'delete'
  }
];

export default function EveningsTable({ evenings, selectedEvening, onEveningSelected, onUpdate, onDelete, readonly }) {
  const handleRowAction = (item, action) => {
    switch (action.value) {
      case 'edit':
        onUpdate(item);
        break;
      case 'delete':
        onDelete(item.date);
        break;
      default:
        console.error('Not defined');
        break;
    }
  };

  if (!evenings.length) {
    return null;
  }

  return (
    <BaseTable
      items={evenings}
      columns={COLUMNS}
      rowActions={readonly ? [] : ROW_ACTIONS}
      onRowAction={handleRowAction}
      enableSelection={!readonly}
      selectedItem={selectedEvening}
      onSelectItem={onEveningSelected}
      maxHeight="40vh"
    />
  );
}
